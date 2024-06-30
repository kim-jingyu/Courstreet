// 비밀번호 찾기 페이지
// 작성자: 김진규

import {
  Container,
  Header,
  Title,
  Button,
  ConfirmButton,
  Form,
  Input,
  InputVerifyCode,
  EmailForm,
  CodeForm,
  InputWithButton,
  Label,
  Timer,
  FormWrapper,
  NameWrapper,
} from './PasswordFind.style';

function PasswordFind() {
  return (
    <Container>
      <Header>
        <Title>비밀번호 찾기</Title>
      </Header>
      <Form>
        <Label>
          이름
          <NameWrapper>
            <Input type="text" placeholder="홍길동" />
          </NameWrapper>
        </Label>
        <Label>
          이메일
          <FormWrapper>
            <EmailForm>
              <InputWithButton type="email" placeholder="이메일을 입력해주세요."></InputWithButton>
              <Button>인증</Button>
            </EmailForm>
          </FormWrapper>
        </Label>
        <Label>
          인증코드
          <FormWrapper>
            <CodeForm>
              <InputVerifyCode type="text" placeholder="인증코드를 입력해주세요." />
              <Timer>03:00</Timer>
            </CodeForm>
          </FormWrapper>
        </Label>
        <ConfirmButton>확인</ConfirmButton>
      </Form>
    </Container>
  );
}

export default PasswordFind;
